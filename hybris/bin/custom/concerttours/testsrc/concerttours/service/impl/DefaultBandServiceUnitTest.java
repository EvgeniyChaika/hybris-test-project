package concerttours.service.impl;

import concerttours.daos.BandDAO;
import concerttours.model.BandModel;
import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UnitTest
public class DefaultBandServiceUnitTest {
    private DefaultBandService bandService;
    private BandDAO bandDAO;
    private BandModel bandModel;
    /**
     * Name of test band.
     */
    private static final String BAND_CODE = "Ch00X";
    /**
     * Name of test band.
     */
    private static final String BAND_NAME = "Singers All";
    /**
     * History of test band.
     */
    private static final String BAND_HISTORY = "Medieval choir formed in 2001, based in Munich famous for authentic monastic chants";

    @Before
    public void setUp() {
        // We will be testing BandServiceImpl - an implementation of BandService
        bandService = new DefaultBandService();
        // So as not to implicitly also test the DAO, we will mock out the DAO using Mockito
        bandDAO = mock(BandDAO.class);
        // and inject this mocked DAO into the BandService
        bandService.setBandDAO(bandDAO);
        // This instance of a BandModel will be used by the tests
        bandModel = new BandModel();
        bandModel.setCode(BAND_CODE);
        bandModel.setName(BAND_NAME);
        bandModel.setAlbumSales(null);
        bandModel.setHistory(BAND_HISTORY, Locale.ENGLISH);
    }

    /**
     * This test tests and demonstrates that the Service's getAllBands method calls the DAOs' getBands method and returns
     * the data it receives from it.
     */
    @Test
    public void testGetAllBands() {
        // We construct the data we would like the mocked out DAO to return when called
        final List<BandModel> bandModels = Collections.singletonList(bandModel);
        //Use Mockito and compare results
        when(bandDAO.findBands()).thenReturn(bandModels);
        // Now we make the call to BandService's getBands() which we expect to call the DAOs' findBands() method
        final List<BandModel> result = bandService.getBands();
        // We then verify that the results returned from the service match those returned by the mocked-out DAO
        assertEquals("We should find one", 1, result.size());
        assertEquals("And should equals what the mock returned", bandModel, result.get(0));
    }

    @Test
    public void testGetBand() {
        // Tell Mockito we expect a call to the DAO's getBand(), and, if it occurs, Mockito should return BandModel instance
        when(bandDAO.findBandsByCode(BAND_CODE)).thenReturn(Collections.singletonList(bandModel));
        // We make the call to the Service's getBandForCode() which we expect to call the DAO's findBandsByCode()
        final BandModel result = bandService.getBandForCode(BAND_CODE);
        // We then verify that the result returned from the Service is the same as that returned from the DAO
        assertEquals("Band should equals() what the mock returned", bandModel, result);
    }
}

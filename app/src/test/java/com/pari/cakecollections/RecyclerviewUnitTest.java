package com.pari.cakecollections;

import android.content.Context;

import com.pari.cakecollections.model.AllCakeList;
import com.pari.cakecollections.model.CakeDetail;
import com.pari.cakecollections.services.GetResponseDataContract;
import com.pari.cakecollections.services.Presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RecyclerviewUnitTest {
    ArrayList<CakeDetail> cakeList;

@Mock
   private AllCakeList allCakeList;

    @Mock
    private GetResponseDataContract.View view;

    @Mock
    private GetResponseDataContract.Presenter presenter;

    @Mock
    private Context context;

    @Mock
    private Call<List<CakeDetail>> cakeDetails;

    @Captor
    private ArgumentCaptor<Callback<List<CakeDetail>>> argumentCaptorCakedetails;

  @Before
    public void setUp(){
      CakeDetail cake = new CakeDetail("Carrot cake","Bugs bunnys favourite","https://hips.hearstapps.com/del.h-cdn.co/assets/18/08/1519321610-carrot-cake-vertical.jpg");
      cakeList = new ArrayList<>();
      cakeList.add(cake);
      context = Mockito.mock(Context.class);
      view = Mockito.mock(GetResponseDataContract.View.class);
      presenter = Mockito.mock(Presenter.class);
      MockitoAnnotations.initMocks(this);
  }

  @Test
    public void testLoadWeatherData_andSucceed() throws Exception {
      String url = "https://gist.githubusercontent.com";
      presenter.getDataFromAPI(context,url);
      Mockito.when(allCakeList.getCakeList()).thenReturn(cakeDetails);
      view.onGetResponseDataSuccess("Success",cakeList);


        }

  @Test
  public void testLoadWeatherData_andFailed() throws Exception {
      String url = "https://gist.com";
      presenter.getDataFromAPI(context,url);
      Mockito.when(allCakeList.getCakeList()).thenReturn(cakeDetails);
      view.onGetResponseDataFailure("Error");
  }





  }
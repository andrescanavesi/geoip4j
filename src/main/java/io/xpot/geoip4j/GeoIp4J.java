/*
 Copyright 2016 Andres Canavesi

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package io.xpot.geoip4j;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 *
 * @author Andres Canavesi
 */
public class GeoIp4J {

    private static final Logger LOG = Logger.getLogger(GeoIp4J.class.getName());
    /**
     * The URL base of the webservice, an invocation example should be:
     *
     * GET http://freegeoip.net/json/167.60.171.196
     */
    public static final String WS_FREEGEOIP_NET = "http://freegeoip.net/";

    private final Gson gson;
    private final Retrofit retrofit;
    private final FreeGeoIpInterface ws;

    private final String wsBaseUrl;

    public GeoIp4J(String wsBaseUrl) {
        this.wsBaseUrl = wsBaseUrl;
        gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(this.wsBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ws = retrofit.create(FreeGeoIpInterface.class);
    }

    /**
     * @param response
     * @throws Exception
     */
    private void validateResponse(Response response) throws Exception {
        if (!response.isSuccessful()) {
            throw new Exception(response.code() + " " + response.message());
        }
    }

    /**
     *
     * @param ipAddress
     * @return
     * @throws Exception
     */
    public FreeGeoIpResponse getGeoInfo(String ipAddress) throws Exception {
        LOG.log(Level.INFO, "Doing request to {0} with the IP address: {1}", new Object[]{wsBaseUrl, ipAddress});
        Call<FreeGeoIpResponse> call = ws.getGeoInfo(ipAddress);
        Response<FreeGeoIpResponse> response = call.execute();
        validateResponse(response);
        return response.body();
    }
}

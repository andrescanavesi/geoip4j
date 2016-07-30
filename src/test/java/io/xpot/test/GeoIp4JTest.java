package io.xpot.test;

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

import io.xpot.geoip4j.FreeGeoIpResponse;
import io.xpot.geoip4j.GeoIp4J;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author andres
 */
public class GeoIp4JTest {

    private static final Logger LOG = Logger.getLogger(GeoIp4JTest.class.getName());

    public GeoIp4JTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void success() {
        try {
            GeoIp4J geoIp4J = new GeoIp4J(GeoIp4J.WS_FREEGEOIP_NET);
            FreeGeoIpResponse info = geoIp4J.getGeoInfo("167.60.171.196");
            Assert.assertEquals("Uruguay", info.getCountryName());
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            Assert.fail(ex.getMessage());
        }

    }
}

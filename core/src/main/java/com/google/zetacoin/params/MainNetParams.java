/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zetacoin.params;

import com.google.zetacoin.core.NetworkParameters;
import com.google.zetacoin.core.Utils;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        // Note: proofOfWorkLimit is changed from litecoinj and zetacoinj because
        // - betacoin satoshi client's bnProofOfWorkLimit is 1d00ffff, not 1e0fffff
        proofOfWorkLimit = Utils.decodeCompactBits(0x1d00ffffL);
        dumpedPrivateKeyHeader = 143;
        addressHeader = 25;
        p2shHeader = 11;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 32333;
        packetMagic = 0xa5c07955L;
        genesisBlock.setDifficultyTarget(0x1d00ffffL);
        genesisBlock.setTime(1382532797L);
        genesisBlock.setNonce(704106316);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 126000;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("000000008ef7da946aa3f4dd81b240c6bdedac0dc038cb04e7cf8e60f37d9281"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        // checkpoints.put(91722, new Sha256Hash("00000000000271a2dc26e7667f8419f2e15416dc6955e5a6c6cdf3f2574dd08e"));
        // checkpoints.put(91812, new Sha256Hash("00000000000af0aed4792b1acee3d966af36cf5def14935db8de83d6f9306f2f"));
        // checkpoints.put(91842, new Sha256Hash("00000000000a4d0a398161ffc163c503763b1f4360639393e0e4c8e300e0caec"));
        // checkpoints.put(91880, new Sha256Hash("00000000000743f190a18c5577a3c2d2a1f610ae9601ac046a38084ccb7cd721"));
        // checkpoints.put(200000, new Sha256Hash("000000000000034a7dedef4a161fa058a2d67a173a90155f3a2fe6fc132e0ebf"));
        // TODO: Is BetaCoin post-BIP30? Do we need these?

        dnsSeeds = new String[] {
                "seed1.betacoin.org",
                 "76.74.177.244"
                        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}

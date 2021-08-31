/*
package com.intuit.config;

import com.intuit.model.AccessRequest;
import com.intuit.repository.AccessRequestRepository;
import com.intuit.service.AccessRequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Import({  AccessRequestService.class })
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(classes = { CacheAutoConfiguration.class, RedisAutoConfiguration.class })
@EnableCaching
class ItemServiceCachingIntegrationTest {

    private static final String AN_ID = "id-1";
    private static final String A_DESCRIPTION = "an item";

    @MockBean
    private AccessRequestRepository mockItemRepository;

    @Autowired
    private AccessRequestService accessRequestService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void givenRedisCaching_whenFindItemById_thenItemReturnedFromCache() {
       // Item anItem = new Item(AN_ID, A_DESCRIPTION);

        AccessRequest accessRequest = new AccessRequest();
        accessRequest.setStatus("Manager");
        accessRequest.setDigitalIdentityNumber("12345");
        accessRequest.setManagerId("2");
        accessRequest.setRequestId(1);
        accessRequest.setRequest("Need Access");

        given(mockItemRepository.findById(1))
                .willReturn(Optional.of(accessRequest));

        AccessRequest itemCacheMiss = accessRequestService.getAcessRequestById(1).get();
        AccessRequest itemCacheHit = accessRequestService.getAcessRequestById(1).get();

        assertThat(itemCacheMiss).isEqualTo(accessRequest);
        assertThat(itemCacheHit).isEqualTo(accessRequest);

        verify(mockItemRepository, times(1)).findById(1);
        assertThat(itemFromCache()).isEqualTo(accessRequest);
    }

    private Object itemFromCache() {
        return cacheManager.getCache("accessRequest").get(1).get();
    }

    @TestConfiguration
    static class EmbeddedRedisConfiguration {

        private final RedisServer redisServer;

        public EmbeddedRedisConfiguration() {
            this.redisServer = new RedisServer();
        }

        @PostConstruct
        public void startRedis() {
            redisServer.start();
        }

        @PreDestroy
        public void stopRedis() {
            this.redisServer.stop();
        }
    }

}
*/

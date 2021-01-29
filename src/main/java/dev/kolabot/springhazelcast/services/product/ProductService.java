package dev.kolabot.springhazelcast.services.product;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final String PRODUCT = "Product";

    private final HazelcastInstance hazelcastInstance;

    IMap<Long, Product> map = null;

    @PostConstruct
    public void init(){
        map = hazelcastInstance.getMap(PRODUCT);
    }

    public void put(Product product) {
        map.putAsync(product.getId(), product);
    }

    public Product get(Long id) {
        return map.get(id);
    }

}

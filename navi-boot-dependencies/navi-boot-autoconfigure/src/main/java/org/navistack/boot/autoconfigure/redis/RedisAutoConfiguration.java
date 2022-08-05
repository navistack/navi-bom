package org.navistack.boot.autoconfigure.redis;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.ResolvableType;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;

@AutoConfiguration
@ConditionalOnClass(RedisOperations.class)
public class RedisAutoConfiguration {
    @Bean
    @Conditional(MissingStringObjectRedisOperations.class)
    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    public RedisOperations<String, Object> StringObjectRedisOperations(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<String> stringRedisSerializer = RedisSerializer.string();
        RedisSerializer<Object> jsonRedisSerializer = RedisSerializer.json();
        RedisTemplate<String, Object> stringObjectRedisTemplate = new RedisTemplate<>();
        stringObjectRedisTemplate.setConnectionFactory(redisConnectionFactory);
        stringObjectRedisTemplate.setKeySerializer(stringRedisSerializer);
        stringObjectRedisTemplate.setValueSerializer(jsonRedisSerializer);
        stringObjectRedisTemplate.setHashKeySerializer(stringRedisSerializer);
        stringObjectRedisTemplate.setHashValueSerializer(jsonRedisSerializer);
        return stringObjectRedisTemplate;
    }

    public static class MissingStringObjectRedisOperations implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
            ResolvableType generics = ResolvableType.forClassWithGenerics(RedisOperations.class, String.class, Object.class);
            String[] names = beanFactory.getBeanNamesForType(generics);
            return names.length == 0;
        }
    }

    @Bean
    @Conditional(MissingStringLongRedisOperations.class)
    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    public RedisOperations<String, Long> stringLongRedisOperations(RedisConnectionFactory redisConnectionFactory) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer(StandardCharsets.UTF_8);
        GenericToStringSerializer<Long> longGenericToStringSerializer = new GenericToStringSerializer<>(Long.class);
        RedisTemplate<String, Long> stringLongRedisTemplate = new RedisTemplate<>();
        stringLongRedisTemplate.setConnectionFactory(redisConnectionFactory);
        stringLongRedisTemplate.setKeySerializer(stringRedisSerializer);
        stringLongRedisTemplate.setValueSerializer(longGenericToStringSerializer);
        stringLongRedisTemplate.setHashKeySerializer(stringRedisSerializer);
        stringLongRedisTemplate.setHashValueSerializer(longGenericToStringSerializer);
        return stringLongRedisTemplate;
    }

    public static class MissingStringLongRedisOperations implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
            ResolvableType generics = ResolvableType.forClassWithGenerics(RedisOperations.class, String.class, Long.class);
            String[] names = beanFactory.getBeanNamesForType(generics);
            return names.length == 0;
        }
    }
}

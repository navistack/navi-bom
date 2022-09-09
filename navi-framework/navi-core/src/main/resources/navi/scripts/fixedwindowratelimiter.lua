--- Fixed Window Rate Limiter
-- @param KEYS[1] Key
-- @param ARGV[1] Max requests per window
-- @param ARGV[2] Expiration
-- @return true if the request should be accepted or false otherwise

if (tonumber(redis.call('GET', KEYS[1])) or 0) < tonumber(ARGV[1]) then
    redis.call('INCR', KEYS[1])
    redis.call('EXPIRE', KEYS[1], ARGV[2])
    return true
else
    return false
end

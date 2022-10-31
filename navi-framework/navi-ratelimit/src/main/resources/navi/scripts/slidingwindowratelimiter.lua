--- Sliding Window Rate Limiter
-- @param KEYS[1] Key
-- @param ARGV[1] Max requests per window
-- @param ARGV[2] UNIX Timestamp in milliseconds
-- @param ARGV[3] Window size in milliseconds
-- @return true if the request should be accepted or false otherwise

redis.call('ZREMRANGEBYSCORE', KEYS[1], 0, tonumber(ARGV[2]) - tonumber(ARGV[3]))
if redis.call('ZCARD', KEYS[1]) < tonumber(ARGV[1]) then
    redis.call('ZADD', KEYS[1], ARGV[2], ARGV[2])
    redis.call('EXPIRE', KEYS[1], math.floor(tonumber(ARGV[3]) / 1000))
    return true
else
    return false
end

-- Periodic Rate Limiter
-- @param KEYS[1] Key
-- @param ARGV[1] Max requests per window
-- @param ARGV[2] Expiration
-- @return true if the request should be accepted or false otherwise

local current = redis.call('INCR', KEYS[1])
if current == 1 then
    redis.call('EXPIRE', KEYS[1], tonumber(ARGV[2]))
end

return current <= tonumber(ARGV[1])

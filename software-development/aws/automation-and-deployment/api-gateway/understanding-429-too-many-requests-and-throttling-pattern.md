# Understanding 429 (Too Many Requests) & Throttling Pattern

### What is 429?

HTTP status code 429 "Too Many Requests" indicates that a user has sent too many requests in a given amount of time. This is also known as "rate limiting" or "throttling."

### Best Practices for Handling 429

1. Server-side:
   * Include `Retry-After` header in the response
   * Use sliding window or token bucket algorithms for accurate rate limiting
   * Maintain separate limits for different API endpoints
2.  Client-side:

    ```javascript
    async function apiCall() {
      try {
        const response = await fetch('/api/resource');
        if (response.status === 429) {
          const retryAfter = response.headers.get('Retry-After');
          await delay(retryAfter * 1000);
          return apiCall(); // Retry request
        }
        return response.json();
      } catch (error) {
        console.error('Request failed:', error);
      }
    }
    ```

### Common Throttling Strategies

1. Fixed Window: Simplest but can allow bursts at window boundaries
2. Sliding Window: More accurate, prevents boundary bursts
3. Token Bucket: Flexible, allows for burst handling while maintaining average rate
4. Leaky Bucket: Smooths out request processing, good for queue-based systems

By implementing proper throttling and 429 handling, you can protect your services while providing a better experience for all users.

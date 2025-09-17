
## Swagger 
[swagger-ui](http://localhost:8080/swagger-ui/index.html)

## Springwolf
[springwolf-ui](http://localhost:8080/springwolf/asyncapi-ui.html)


```java
public class CreditScoreUtils {

    public static Map<Integer, Integer> creditScoreResults() {
        Map<Integer, Integer> creditScoreMap = new HashMap<>();
        creditScoreMap.put(101, 760);
        creditScoreMap.put(202, 340);
        creditScoreMap.put(3287, 800);
        creditScoreMap.put(2362, 550);
        return creditScoreMap;
    }
}
```

```bash
curl -X 'POST' 'http://localhost:8080/api/loans/process' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "userId": 202,
  "amount": 100000
}'
```

```bash
curl -X 'GET' 'http://localhost:8080/api/loans/status?loanId=2'
```
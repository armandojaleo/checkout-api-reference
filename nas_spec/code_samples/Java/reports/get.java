// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.reports.ReportsQuery;
import com.checkout.reports.ReportsResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

// API Keys
CheckoutApi api = CheckoutSdk.builder()
    .staticKeys()
    .secretKey("secret_key")
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

// OAuth
CheckoutApi api = CheckoutSdk.builder()
    .oAuth()
    .clientCredentials("client_id", "client_secret")
    .scopes(OAuthScope.REPORTS, OAuthScope.REPORTS_VIEW) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

ReportsQuery queryFilterDateRange = ReportsQuery.builder()
    .createdAfter(LocalDateTime.now().minus(1, ChronoUnit.WEEKS).toInstant(ZoneOffset.UTC))
    .createdBefore(Instant.now())
    .build();

try {
    ReportsResponse response = api.reportsClient().getAllReports(queryFilterDateRange).get();
} catch (CheckoutApiException e) {
    // API error
    String requestId = e.getRequestId();
    int statusCode = e.getHttpStatusCode();
    Map<String, Object> errorDetails = e.getErrorDetails();
} catch (CheckoutArgumentException e) {
    // Bad arguments
} catch (CheckoutAuthorizationException e) {
    // Invalid authorization
}

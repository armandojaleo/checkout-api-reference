// For more information please refer to https://github.com/checkout/checkout-sdk-java
import com.checkout.CheckoutApi;
import com.checkout.CheckoutApiException;
import com.checkout.CheckoutArgumentException;
import com.checkout.CheckoutAuthorizationException;
import com.checkout.CheckoutSdk;
import com.checkout.Environment;
import com.checkout.OAuthScope;
import com.checkout.metadata.card.CardMetadataFormatType;
import com.checkout.metadata.card.CardMetadataRequest;
import com.checkout.metadata.card.CardMetadataResponse;
import com.checkout.metadata.card.source.CardMetadataCardSource;

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
    .scopes(OAuthScope.VAULT_CARD_METADATA) // more scopes available
    .environment(Environment.SANDBOX) // or Environment.PRODUCTION
    .build();

CardMetadataRequest metadataCardRequest = CardMetadataRequest.builder()
    .source(CardMetadataCardSource.builder()
        .number("4242424242424242")
        .build())
    .format(CardMetadataFormatType.BASIC)
    .build();

try {
    CardMetadataResponse response = api.metadataClient().requestCardMetadata(metadataCardRequest).get();
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
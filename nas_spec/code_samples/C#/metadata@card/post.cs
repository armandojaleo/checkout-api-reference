// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Metadata.Card;
using Checkout.Metadata.Card.Source;

//API keys
ICheckoutApi api = CheckoutSdk.Builder().StaticKeys()
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

//OAuth
ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.VaultCardMetadata)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

CardMetadataRequest request = new CardMetadataRequest
{
    Source = new CardMetadataCardSource { Number = "4242424242424242" },
    Format = CardMetadataFormatType.Basic
};

try
{
    CardMetadataResponse response = await api.MetadataClient().RequestCardMetadata(request);
}
catch (CheckoutApiException e)
{
    // API error
    string requestId = e.RequestId;
    var statusCode = e.HttpStatusCode;
    IDictionary<string, object> errorDetails = e.ErrorDetails;
}
catch (CheckoutArgumentException e)
{
    // Bad arguments
}
catch (CheckoutAuthorizationException e)
{
    // Invalid authorization
}
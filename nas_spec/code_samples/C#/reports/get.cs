// For more information please refer to https://github.com/checkout/checkout-sdk-net
using Checkout.Reports;

//API keys
ICheckoutApi api = CheckoutSdk.Builder().StaticKeys()
    .SecretKey("secret_key")
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

//OAuth
ICheckoutApi api = CheckoutSdk.Builder().OAuth()
    .ClientCredentials("client_id", "client_secret")
    .Scopes(OAuthScope.Reports, OAuthScope.ReportsView)
    .Environment(Environment.Sandbox)
    .HttpClientFactory(new DefaultHttpClientFactory())
    .Build();

ReportsQuery query = new ReportsQuery
{
    CreatedAfter = DateTime.Now.Subtract(TimeSpan.FromDays(7)),
    CreatedBefore = DateTime.Now
};

try
{
    ReportsResponse response = await api.ReportsClient().GetAllReports(query);
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
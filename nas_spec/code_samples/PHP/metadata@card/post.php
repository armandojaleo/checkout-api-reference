<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php
use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\OAuthScope;
use Checkout\\Metadata\\Card\\CardMetadataFormatType;
use Checkout\\Metadata\\Card\\CardMetadataRequest;
use Checkout\\Metadata\\Card\\Source\\CardMetadataCardSource;

//API Keys
$api = CheckoutSdk::builder()->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$VaultCardMetadata])
    ->environment(Environment::sandbox())
    ->build();

$cardMetadataRequest = new CardMetadataRequest();
$cardMetadataRequest->format = CardMetadataFormatType::$BASIC;
$cardMetadataRequest->source = new CardMetadataCardSource();
$cardMetadataRequest->source->number = "4242424242424242";

try {
    $response = $api->getMetadataClient()->requestCardMetadata($cardMetadataRequest);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
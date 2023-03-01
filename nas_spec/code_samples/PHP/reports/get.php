<?php
//For more information please refer to https://github.com/checkout/checkout-sdk-php
use Checkout\\CheckoutApiException;
use Checkout\\CheckoutAuthorizationException;
use Checkout\\CheckoutSdk;
use Checkout\\Environment;
use Checkout\\OAuthScope;
use Checkout\\Reports\\ReportsQuery;
use DateInterval;
use DateTime;
use DateTimeZone;

//API Keys
$api = CheckoutSdk::builder()->staticKeys()
    ->environment(Environment::sandbox())
    ->secretKey("secret_key")
    ->build();

//OAuth
$api = CheckoutSdk::builder()->oAuth()
    ->clientCredentials("client_id", "client_secret")
    ->scopes([OAuthScope::$Reports, OAuthScope::$ReportsView])
    ->environment(Environment::sandbox())
    ->build();

$created_after = new DateTime();
$created_after->setTimezone(new DateTimeZone("europe/madrid"));
$created_after->sub(new DateInterval("P7D"));

$query = new ReportsQuery();
$query->created_after = $created_after;
$query->created_before = new DateTime();

try {
    $response = $api->getReportsClient()->getAllReports($query);
} catch (CheckoutApiException $e) {
    // API error
    $error_details = $e->error_details;
    $http_status_code = isset($e->http_metadata) ? $e->http_metadata->getStatusCode() : null;
} catch (CheckoutAuthorizationException $e) {
    // Bad Invalid authorization
}
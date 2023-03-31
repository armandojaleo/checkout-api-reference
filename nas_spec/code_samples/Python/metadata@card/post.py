# For more information please refer to https://github.com/checkout/checkout-sdk-python
import checkout_sdk
from checkout_sdk.checkout_sdk import CheckoutSdk
from checkout_sdk.environment import Environment
from checkout_sdk.exception import CheckoutApiException, CheckoutArgumentException, CheckoutAuthorizationException
from checkout_sdk.oauth_scopes import OAuthScopes
from checkout_sdk.metadata.metadata import CardMetadataRequest, CardMetadataCardSource, CardMetadataFormatType

# API Keys
api = CheckoutSdk.builder() \\
    .secret_key('secret_key') \\
    .environment(Environment.sandbox()) \\
    .build()
    # or Environment.production()

# OAuth
api = CheckoutSdk.builder() \\
    .oauth() \\
    .client_credentials('client_id', 'client_secret') \\
    .environment(Environment.sandbox()) \\
    .scopes([OAuthScopes.VAULT_CARD_METADATA]) \\
    .build()

card_metadata_card_source = CardMetadataCardSource()
card_metadata_card_source.number = '4242424242424242'

card_metadata_request = CardMetadataRequest()
card_metadata_request.source = card_metadata_card_source
card_metadata_request.format = CardMetadataFormatType.BASIC

try:
    response = api.card_metadata.request_card_metadata(card_metadata_request)
except CheckoutApiException as err:
    # API error
    request_id = err.request_id
    status_code = err.http_status_code
    error_details = err.error_details
    http_response = err.http_response
except CheckoutArgumentException as err:
# Bad arguments

except CheckoutAuthorizationException as err:
# Invalid authorization

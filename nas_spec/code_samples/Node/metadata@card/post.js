import { Checkout } from 'checkout-sdk-node';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	let cardMetadata = await cko.cardMetadata.get({
		source: {
			type: 'card',
			number: '4242424242424242'
		},
		format: 'basic'
	});
} catch (err) {
	console.log(err.name);
}

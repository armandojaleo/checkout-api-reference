import { Checkout } from 'checkout-sdk-node';

let cko = new Checkout('your_client_secret_here', {
	client: 'ack_XXXXXXXXXXXX',
	scope: ['gateway'], // array of scopes
	environment: 'sandbox', // or "production"
});

// Or if you use api keys:
// const cko = new Checkout('sk_sbox_XXX', { pk: 'pk_sbox_XXX'}});

try {
	const getAllReports = await cko.reports.getAllReports({
		created_after: '2022-02-17',
		created_before: '2022-02-19',
		entity_id: 'ent_znj4ih5kn4fuxiaquoudv5mvwyt',
		limit: 5,
		pagination_token: 'NaZMwq3KbreYcXg0dg752Dg8ps4orkwVK9pj9WFzkXk8rPoR32Wf74QWX0EkZ'
	});
} catch (err) {
	console.log(err.name);
}

# Troubleshooting

### I have a working Elastic IP address and I have registered the facegov.com domain with AWS. now I want to configure the facegov domain to connect to the Elastic IP address. What are the steps?

To configure your facegov.com domain to connect to your Elastic IP address in AWS, you'll need to use Amazon Route 53. Here are the steps:

1. Open the AWS Management Console and navigate to the Route 53 service.
2. In the left sidebar, click on "Hosted zones".
3. If you don't see facegov.com listed, create a new hosted zone:
   * Click "Create hosted zone"
   * Enter "facegov.com" as the domain name
   * Choose "Public hosted zone" for the type
   * Click "Create hosted zone"
4. Once you have the hosted zone for facegov.com:
   * Click on the hosted zone to open it
   * Click "Create record"
5. In the "Create record" form:
   * Leave the "Record name" blank if you want to use the root domain (facegov.com)
   * For "Record type", select "A - Routes traffic to an IPv4 address and some AWS resources"
   * Toggle "Alias" to "No"
   * In the "Value" field, enter your Elastic IP address
   * Set TTL (Time to Live) as desired (3600 seconds is common)
   * Click "Create records"
6. Update your domain's name servers:
   * In your hosted zone, note the four name servers listed under "NS" (Name Server) record
   * Go to your domain registrar (where you registered facegov.com)
   * Update the name servers for facegov.com to use the four Route 53 name servers
7. Wait for DNS propagation. This can take up to 48 hours, but often happens much faster.

After these steps, facegov.com should resolve to your Elastic IP address.

Some additional considerations:

* If you want to use "[www.facegov.com](http://www.facegov.com/)", create another A record with "www" as the name.
* Consider creating a CNAME record for "www" pointing to your root domain for flexibility.
* If you're running a web server, ensure it's configured to handle requests for facegov.com.

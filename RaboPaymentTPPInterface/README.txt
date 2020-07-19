1. Import Project as a Maven Project
2. Setup for Java 8 , Spring boot
3. Use Postman for Testing the endpoint
4. Construct Request Object & headers
 Sample Request Headers-

Content-Type:application/json
X-Request-Id:12345
Signature:XvzTqQbwDjOh+DPOiC7X35cr71a7uZIw4RX88D8bfmOKHx/OPJ8TWo52Rv/KMBmoY1bOOUg/ScDN  B+vF0zIM3DHuz0u9MUj44MIT/SDiL2qbUh2Vo8QjclaZdO2mdZQcM8yLIqj7C05kc+KQ7oxDGRYV  VuB8sBuJ5rFzcfkpqbE=
Signature-Certificate:Sandbox-TPPcSedxGR5FbPU/3bLdFwxHQ9K8dcCyEqvR7r4RHV1cFENJf8iAh64kDIHSp4bFUteea05EjjqOZo1  ARmzZXCx+oEZHEdGcO8NHnbkXIwDoWSeF/pcKU2z7PSo1Yfk9XYUZaoWIAOsPQst2gUp94dlcUrg  sj4ebIeW/rbH+iiHjNg=

Sample Request Object
{
	"debtorIBAN":"abc123ABC45",
	"creditorIBAN":"bcd12345",
	"amount":"12.0",
	"currency":"E6R"
}
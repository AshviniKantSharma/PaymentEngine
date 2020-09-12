1. Import Project as a Maven Project
2. Setup for Java 8 , Spring boot
3. Use Postman for Testing the endpoint
4. Added Unit test cases for positive & negative scenario
5. Data based testing not covered, as it is subjective
6. Valid product types accepted - Book,PhysicalProduct,Membership,Video
7. Below are Sample Cases & Responses, kindly go through the sample cases to get an idea of the app
Case1 - Invalid Product 

Sample Request Object
{
	"orderId":"",
	"productType":"TYPE"
}
Response

{
"timestamp": "2020-09-12T11:18:58.426+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Product Type not supported",
    "trace": "java.lang.Exception: Product Type not supported...
    "path": "/payment/v1.0.0/initiate-order"
}

Case2 - Valid Product 

Sample Request Object
{
	"orderId":"",
	"productType":"Membership"
}
Response

{
    "orderId": "5d7c6d4e-f224-4894-88ad-10b162822de6",
    "status": "Activate",
    "promotionMessage": "None"
}

Case3 - Valid Product / Existing

Sample Request Object
{
	"orderId":"5d7c6d4e-f224-4894-88ad-10b162822de6",
	"productType":"Membership"
}
Response

{
    "orderId": "5d7c6d4e-f224-4894-88ad-10b162822de6",
    "status": "Activate",
    "promotionMessage": "Upgrade"
}
# Iaido Code Test
This is my submission for the Iaido code test, this was a wonderfully learning experience and I believe I have created
a well refined product.

# Docker Deployment

Optimized Spring Boot docker by splitting Spring Boot's fat jar into layers to build one singular layer and reuse the 
duplicate layers from the host cache.

docker build . -t iadioexam<br>
docker run -p 8080:8080 iadioexam

# Postman Testing
I developed and testing all of my individual API routes through Postman.<br>
The endpoints for the application are as follows:<br>

<ul>
  <li>/persons&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: GET &nbsp;&nbsp;: ?page=0&amount=1</li>
  <li>/searchPersons : GET &nbsp;&nbsp;: ?name=Charlie&age=22</li>
  <li>/persons/{id}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: GET &nbsp;&nbsp;: /persons/1</li>
  <li>/persons &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: POST : JSON DATA</li>
  <li>/persons/{id} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: PUT &nbsp;&nbsp;: JSON DATA</li>
  <li>/persons/{id} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: DELETE : /persons/1</li>
</ul>

<img src="https://i.imgur.com/pX3Qh2C.png">
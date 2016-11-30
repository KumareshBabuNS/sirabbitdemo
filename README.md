# Spring Integration RabbitMQ Demo
Demo showing Spring Integration and RabbitMQ working together on Pivotal Cloud Foundry.

The demo consists of three components, a transmitter, processor and receiver. Each of these components are deployed as indivicual applications and are communicating using RabbitMQ.

## How to Build and Deploy Demo
* If no installation of PCF is available, download and install PCF Dev from https://network.pivotal.io/products/pcfdev
* Start PCF Dev and log on to Cloud Foundry CLI

  See https://docs.pivotal.io/pcf-dev/ for more information on using PCF Dev
* Run the following script to build and deploy demo:

  ```
  build-deploy-pcfdev.sh
  ```

## How to run the demo
* Watch the logs from the rabbit-receiver in one terminal window

  ```
  cf logs rabbit-receiver
  ```
* Watch the logs from the rabbit-processor in another terminal window

  ```
  cf logs rabbit-processor
  ```
* Send a message to the transmitter application using the following command

  ```
  curl http://rabbit-webtransmitter.local.pcfdev.io/1
  ```

Watch the processor logs to see the message get processed and the receiver app to see the XML result.

Lesson 1.1: What is Apache Kafka?

	In your own words, explain why Kafka is useful for building modern applications.7
	
	kafka is a streaming platform that is used to communicate between two services and hence breaking the barrier of direct interaction with services and making is independent of each other. Two services is decoupled if we use kafka for communicating between them.
	
	If you have a banking application where you need to track "transaction" events and "login attempt" events, how would you likely organize these in Kafka using topics?
	
	For login attempts we can use kafka by sending the details to login_successfull and login_failure topic of kafka. If the login is successfull the login_successfull topic will have records/messages and the consumer listening to this topic will send message to user mobile number with details like location or new device. If login is failed then the login_failure topic will have records/messages and the consumer of this topic will send message to user telling login attempt failed and number of retry option left. Similarly for transaction events if user sends some money in that case also we send user notification with transaction amount and transaction_id details via transaction_details topics
	
	What's the purpose of a "producer" in Kafka? What about a "consumer"?
	
	producer in kafka is something that produces data and sends to kafka topic. Similarly, consumer in kafka is used to consume the data being sent to kafka from producer.
	
	Why are "partitions" important for Kafka's performance?
	Partitions are important in case we have millions of records getting created via producer now for those data we need consumer also to consume it. but the rate at which producer produce is higher than the rate at which consumer consume then in that case kafka will have processing and performance issue. So, to overcome that we use partitioning of topics into multiple partition. We can now assign multiple consumer to these partition. One partition will have one consumer. ALL the data being pushed can be sent based on key. Kafka creates a hashing of these keys and accordingly assign the partition to that key. So data with that type of key will  go to that type partition and hence consumer.


Lesson 1.2: Setting Up Your Development Environment

	running zookeeper
	
		.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
	running kafka server(broker)
	
		.\bin\windows\kafka-server-start.bat .\config\server.properties
	create topic with one partition and one replication factor
	
		.\bin\windows\kafka-topics.bat --create --topic my-second-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
		
	create producer that produces record to my-second-topic topic
	
		.\bin\windows\kafka-console-producer.bat --topic my-second-topic --bootstrap-server localhost:9092
		
	create consumer that consume record from my-second-topic topic
	
		.\bin\windows\kafka-console-consumer.bat --topic my-first-topic --from-beginning --bootstrap-server localhost:9092
		
	here --from-beginning is used to read data from beginning of the data produced so from start
	
	to see all the topics
	
	.\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
	
	to see specific topic details
	
	.\bin\windows\kafka-topics.bat --topic my-second-topic --describe --bootstrap-server localhost:9092
	
Lesson 1.3: Deeper Dive into Kafka Concepts	
	
	Why is a high replication-factor important for critical topics in Kafka?

		replication-factor is used to create a copy of the partition on different broker/kafka-server for fault-tolerance. Replication-factor is some numeric value if set as 3 then in total we will have 3 replicas out of which one will act as Leader and other two act as follower. So it one copy is not accessible then other two are their to serve the request. So, in this case you will provide topic with high availability.


	Explain the difference between a Kafka "Leader" and "Follower" partition. Why is this distinction important?

		Leader and Follower is two type of replicas. Leader is someone where the producer writes data and consumer consume the data from Leader only. Follower is something which comes into picture when we have Leader getting down then the Follower gets elected as Leader automatically. Follower has the data in sync of the Leader. You can call them in-sync replicas.


	You have a topic with 5 partitions. You want to process messages from this topic with maximum parallelism using a single logical application. How many consumers would you put in its consumer group, and why?

		As a rule of thumb each partition is suppose to have there respective single consumer. So 5 partitions means 5 consumer. In a consumer group i will put 5 consumer. 


	If you produce two messages, "Message A" and "Message B", to the same topic, but they end up in different partitions, can you guarantee that a consumer will read "Message A" before "Message B"? Why or why not?

		No we cannot gaurantee the order of records being consumed by consumer as we have two partitions. To gaurantee the ordering we will have to make this topic with single partition.


	What's the benefit of Kafka's message retention policy?

		kafka's message retention policy is used for saving the data for long duration. In case we need some data from past then we can access it by setting some retention policy. It is configuration based. According to project or application requirement we can use this config.

Lesson 2.2: Building a Kafka Producer in Spring Boot 

	In your own words, briefly explain the role of KafkaTemplate in Spring Boot.
	
		KafkaTemplate is a class that is autoconfigured when using spring-boot library spring for Apache kafka. It has two generic type one for key and another for value.


	Why did we use StringSerializer for both key and value in our application.properties?

		Kafka accepts the data in byte format that it saves data in byte format and not in raw format so we are using StringSerializer to do the byte conversion of the data and key being sent. 
	
Lesson 2.3: Building a Kafka Consumer in Spring Boot

	What is the primary role of the @KafkaListener annotation?

		the method that gets annotated with @KafkaListener acts as a consumer method for processing of the record/message. As far as i understand the method structure for the method that gets annotated with @KafkaListener has one argument required that is for message and other metadata like key, Timestamptype, topic, offset and e.tc. can be accessed using @Header annotation. @KafkaListener annotation has two arguments one is for topic and another is group-id or consumer-group.

	Why is it important to define a group-id for a Kafka consumer?

		group-id in spring boot concept is similar to consumer-group in kafka. So whatever consumer method you define with that group-id will come under same consumer-group.
	
		It enables scalability and parallelism by allowing multiple consumer instances in the same group to share partitions.
	
		It ensures fault tolerance within the consumer application, as partitions are reassigned if a consumer instance fails.
	
		It allows independent processing by different applications (different consumer groups can read the same messages from a topic without interfering with each other's offsets).

	What does auto-offset-reset=earliest mean in the context of a consumer starting up? When might you use latest instead?

		This similar to --from-beginning option in kafka consumer. So whenever there is a read going to happen it will do from the start. If initially i have started my consumer it will read all the data then keep processing new message coming to the topic. It reads from start if no prior offset exists for that consumer group 

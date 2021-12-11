Configurable Logging Library

We are required to build our own logging framework that is configurable. Our aim is to support different logging levels
which will save logs in different destinations like console, txt file, network, etc.

Functional Requirement

It should have a facility to log messages at different levels DEBUG, INFO, WARNING, ERROR and a method to control these
levels independently. Provide a configurable option to save each level logs at multiple destinations. Use configuration
which will be used during bootup of the application. Eg: Save Info Level logs at console as well as text file or save
Error level logs in all the destinations. A configurable option for other applications to define the level of logs which
need to be saved and it should follow the hierarchy. Eg: If Log Level is set to WARNING, then all the log levels which
are above WARNING will also get saved. Provide an option for other applications to change log level at run time for each
destination. It should support the option to pass the arguments with the message.

Log Level Hierarchy

ERROR <- WARNING <- INFO <- DEBUG

Instructions

Use Two types of destinations: Console, Text File

Bonus Functionality:

Additional Network based storage as destination. For saving logs over the network, use any Collection. Asynchronously
save logs in the network destination i.e. request should not be blocked for the completion of saving logs over the
network and it should maintain the insertion order also in a multithreaded environment.

Example:

While Bootup the application, read the predefined value in the configuration file. Method names could be anything but
the log format should remain intact.

Write Logs:
log.info(“This is my first log with Id: {}”, idValue); log.error(“I got the error: {}”, errorMessage); log.debug(“This
is just to debug the object: {} with Id: {}”, object, idValue); Sample out:

INFO [2021-12-02T16:00:49+05:30] This is my first log with Id: 100

( filename and line number is not mandatory in output of log)

Change Logging Level:
log.setLevel(textDestination, WARNING); Add/Remove any Platform log.attachPlatform(networkDestination);
log.deattachPlatform(textDestination);

Output: Will be written in Console or File or Network(collection) or in the combination of all three based on the
configuration and run time changes.

Guidelines:

Time: 90mins Write modular and clean code with correct design patterns. A test class is needed to test all the
functionality, try to cover the scenarios as much as possible. Evaluation criteria:  Demoable & functionally correct
code, Code readability, Proper Entity modelling, Modularity & Extensibility, Separation of concerns, Abstractions. Use
design patterns wherever applicable You are not allowed to use any external databases like MySQL. Use only in memory
data structures. Please focus on the Bonus Feature only after ensuring the required features are complete and demoable.

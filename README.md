## Mpesa Java SDK
 A Java library for communicating with Mpesa Daraja rest API. 
 > The SDK provides convenient access to the Mpesa Daraja API from applications written in Java.

## Install
Use the following dependency in your project to grab via Maven:
```
  <dependency>
    <groupId>com.github.corneliouzbett</groupId>
    <artifactId>mpesa-sdk</artifactId>
    <version>1.1-SNAPSHOT</version>
    <type>pom</type>
  </dependency>
```
 
## Usage
The SDK needs to be initialized with your consumer key and consumer secret, which you get from the [safaricom developers portal](https://developer.safaricom.co.ke). You are required to create an app.

>You can use this SDK for either production or sandbox apps.

## Initialize the client
```java
  String consumerKey = "AXXXX-XXXX"; //consumer key
  String consumerSecret = "WEDX-XXXX-XXX-XXX"; consumer secret

  Mpesa mpesa = new MpesaClient(consumerKey, consumerSecret); // initializing mpesa client
```
## Note
This Mpesa SDK is currently under active development, so expect rapid changes

## License
MIT License

Copyright (c) 2020 corneliouz bett

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

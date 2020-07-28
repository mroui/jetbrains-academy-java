# Encryption-Decryption
:muscle: Medium :link: [hyperskill](https://hyperskill.org/projects/46)

>Privacy is an important matter is the realm of the Internet. When sending a message, you want to be sure that no-one but the addressee with the key can read it. The entirety of the modern Web is encrypted - take https for example! Don’t stay behind: hop on the encryption/decryption train and learn the essential basics while implementing this simple project.

## Learning outcomes
Gradually get familiar with Java, paying special attention to working with files and the command line.
|||||||||
|-|-|-|-|-|-|-|-|
|#java-basics|#console|#encryption|#decryption|#shift-encryption|#parsing-arguments|#factory-method|#design-pattern|

## Usage
![Encryption-decryption demo gif](demo.gif)

## Setup
* [Install JDK](https://www.oracle.com/pl/java/technologies/javase-downloads.html)
* Clone repository
```
git clone https://github.com/mroui/jetbrains-academy-java.git
```
* Enter Encryption-decryption directory
```
cd jetbrains-academy-java/Encryption-Decryption
```
* Run with Gradle Encryption-Decryption task. Without passed arguments, you will see possible options.
```
./gradlew -PmainClass=encryptdecrypt.Main Encryption-Decryption-task:run
```
* Run project with specified arguments in ```--args```, for example:
```
./gradlew -PmainClass=encryptdecrypt.Main Encryption-Decryption-task:run --args="-mode enc -data 'Hello world' -key 5 -alg shift"
./gradlew -PmainClass=encryptdecrypt.Main Encryption-Decryption-task:run --args="-mode dec -in 'input.txt' -out 'output.txt' -key 3 -alg unicode"
```

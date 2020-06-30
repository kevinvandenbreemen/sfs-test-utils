# sfs-test-utils
Utilities for testing code that uses the NewCryptoFramework

# How to Use This
## Creating a Test SFS

```
val file = SFSTestingUtils.getTestFile("testFile")
val sfs = SFSTestingUtils.getNewSecureFileSystem(file)
sfs.touch("testFile1")
val credentials = SFSCredentials(file,
SecureFileSystem.generatePassword(SecureString("password123".toByteArray())))
```

Note that the password will always be "password123"
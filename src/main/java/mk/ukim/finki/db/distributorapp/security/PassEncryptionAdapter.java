//package mk.ukim.finki.db.distributorapp.security;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class PassEncryptionAdapter implements PasswordEncoder {
//
//    private final PassEncryption passEncryption;
//
//    public PassEncryptionAdapter(PassEncryption passEncryption) {
//        this.passEncryption = passEncryption;
//    }
//
//    @Override
//    public String encode(CharSequence rawPassword) {
//        String salt = this.passEncryption.
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        String[] parts = encodedPassword.split(":");
//        if(parts.length!=2){
//            return false;
//        }
//
//        String salt = parts[0];
//        String storedPass = parts[1];
//
//        return passEncryption.verifyUserPassword(rawPassword.toString(),storedPass,salt);
//    }
//}

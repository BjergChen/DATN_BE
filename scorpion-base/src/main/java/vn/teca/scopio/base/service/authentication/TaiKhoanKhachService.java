package vn.teca.scopio.base.service.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.authentication.TaiKhoanKhachDtoLogin;
import vn.teca.scopio.base.repository.authentication.TaiKhoanKhachRepository;

import java.util.Optional;

@Service
public class TaiKhoanKhachService {
    @Autowired
    private TaiKhoanKhachRepository repository;

    public Optional<TaiKhoanKhachDtoLogin> findTaiKhoan(String soDienThoai, String password) {
        Optional<TaiKhoanKhachDtoLogin> optionalTaiKhoan = Optional.ofNullable(repository.getInfoKhachByLogin(soDienThoai, password));

        if (optionalTaiKhoan.isPresent()) {
            // Result is present and not null
            TaiKhoanKhachDtoLogin taiKhoanKhachDtoLogin = optionalTaiKhoan.get();
            // Do something with taiKhoanNVDtoLogin
            return Optional.of(taiKhoanKhachDtoLogin);
        } else {
            // Handle the case where the result is not present or is present but null
            System.out.println("Login failed. Invalid phone number or password.");
            return Optional.empty();
        }
    }
    //get all info from sign up the check duplicate and add new info then return a message no matter what
    public String signUpTaiKhoanKhach(String hoVaTen, String soDienThoai, String email, boolean gioiTinh, String password) {
        String message;
        if(repository.isExistSoDienThoai(soDienThoai) == 1){
            message = "số điện thoại này đã được đăng ký trên 1 tài khoản";
        }else {
            message = "đăng ký thành công";
            repository.addNewInfoTaiKhoanKhachAndThongTinKhachDat(hoVaTen,soDienThoai,email,gioiTinh==true?1:0,password);
        }
        return message;
    }
}

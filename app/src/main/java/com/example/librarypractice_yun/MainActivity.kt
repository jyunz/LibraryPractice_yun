package com.example.librarypractice_yun

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileImg.setOnClickListener {

//        프로필 크게 보는 액티비티로 이동.
            val myIntent = Intent(this, ViewProfilePhotoActivity::class.java)
            startActivity(myIntent)

        }

//        인터넷에 있는 이미지 (https://로 시작) 를 이미지 뷰에 바로 대입

        Glide.with(this).load("https://shop-phinf.pstatic.net/20201119_124/1605760157688n7oYn_PNG/3537848473_Hv5dt9nX_c908276496beb058d373b6c527206ce7608aa013.png?type=w336").into(classImg)

//        전화걸기 버튼을 누르면 권한 확인 / 전화연결

        callBtn.setOnClickListener {

//            라이브러리 활용 -> 전화 권한 확인 -> 실제 전화 연결

            val permissionListener = object : PermissionListener {
                override fun onPermissionGranted() {

//                    권한승인 => 실제로 전화 연결 진행

                    val myUri = Uri.parse("tel: 010-3333-3333")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)

                    startActivity(myIntent)

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

//                    권한 거절시 -> 토스트로 권한이 없어서 전화연결 실패 했다는 메세지 띄움
                    Toast.makeText(this@MainActivity,"권한이 없어서 전화 연결에 실패 했습니다.", Toast.LENGTH_SHORT).show()
                }


            }

        }
    }
}

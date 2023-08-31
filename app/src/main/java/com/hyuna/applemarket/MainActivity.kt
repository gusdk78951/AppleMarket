package com.hyuna.applemarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.icu.lang.UCharacter.VerticalOrientation
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyuna.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 리스트
        val dataList = mutableListOf<MainItem>()
        dataList.add(MainItem(R.drawable.sample1,"산진 한달된 선풍기 팝니다","서울 서대문구 창천동",1000))
        dataList.add(MainItem(R.drawable.sample2,"김치냉장고","인천 계양구 귤현동",20000))
        dataList.add(MainItem(R.drawable.sample3,"샤넬 카드지갑","수성구 범어동",10000))
        dataList.add(MainItem(R.drawable.sample4,"금고","해운대구 우제2동",10000))
        dataList.add(MainItem(R.drawable.sample5,"갤럭시Z플립3 팝니다","연제구 연산제8동",150000))
        dataList.add(MainItem(R.drawable.sample6,"프라다 복조리백","수원시 영통구 원천동",50000))
        dataList.add(MainItem(R.drawable.sample7,"울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장","남구 옥동",150000))
        dataList.add(MainItem(R.drawable.sample8,"샤넬 탑핸들 가방","동래구 온천제2동",180000))
        dataList.add(MainItem(R.drawable.sample9,"4행정 엔진분무기 판매합니다.","원주시 명륜2동",30000))
        dataList.add(MainItem(R.drawable.sample10,"셀린느 버킷 가방","중구 동화동",190000))

        binding.recyclerView.adapter = MainAdapter(dataList)

        val adapter = MainAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // 리스트 구분선
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val decoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        // 알림
        binding.notificationButton.setOnClickListener {
            notification()
        }
    }

    // 알림
    fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  // 26 버전 이상
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                // 채널에 다양한 정보 설정
                description = "My Channel One Description"
                setShowBadge(true)   // 뱃지
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)  //소리
                enableVibration(true)  // 진동
            }
            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this, channelId)
        } else {  // 26 버전 이하
            builder = NotificationCompat.Builder(this)
        }
        // 알림의 기본 정보
        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())  // 알람 발생시간 = 현재시간
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
        }
        manager.notify(11, builder.build())
    }

    // 뒤로가기 눌렀을 때 앱 종료
    override fun onBackPressed() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("종료")
        builder.setMessage("정말 종료하시겠습니까?")
        builder.setIcon(R.drawable.chat)

        val listener = object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                when (p1) {
                    DialogInterface.BUTTON_POSITIVE ->
                        finish()
                }
            }
        }
        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", listener)
        builder.show()
    }
}


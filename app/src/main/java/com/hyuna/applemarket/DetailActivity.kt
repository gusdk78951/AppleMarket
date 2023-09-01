package com.hyuna.applemarket

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hyuna.applemarket.databinding.ActivityDetailBinding
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 상단 화살표 클릭 시 MainActivity로 이동
        val arrow = findViewById<ImageButton>(R.id.arrow)
        arrow.setOnClickListener {
            val intent = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // MainActivity에서 받아온 값 나타내기
        val name = intent.getStringExtra("name")
        val location = intent.getStringExtra("location")
        val price = intent.getIntExtra("price", 0)

        val nameTextView = findViewById<TextView>(R.id.name)
        nameTextView.text = name

        val locationTextView = findViewById<TextView>(R.id.location)
        locationTextView.text = location

        val priceTextView = findViewById<TextView>(R.id.price)
        val formattedPrice= NumberFormat.getNumberInstance(Locale.getDefault()).format(price)
        priceTextView.text= formattedPrice // 천단위 마다 콤마(,) 추가

        // name(상품명)에 따라 맞는 값 나타내기 (이미지, 판매자, 상세설명)
        val image = findViewById<ImageView>(R.id.image)
        val user = findViewById<TextView>(R.id.user)
        val detail = findViewById<TextView>(R.id.detail)

        if(name == "산진 한달된 선풍기 팝니다") {
            image.setImageResource(R.drawable.sample1)
            user.text = "대현동"
            detail.text = "이사가서 필요가 없어졌어요 급하게 내놓습니다"
        } else if(name == "김치냉장고") {
            image.setImageResource(R.drawable.sample2)
            user.text = "안마담"
            detail.text = "이사로인해 내놔요"
        } else if(name == "샤넬 카드지갑") {
            image.setImageResource(R.drawable.sample3)
            user.text = "코코유"
            detail.text = "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다"
        } else if(name == "금고") {
            image.setImageResource(R.drawable.sample4)
            user.text = "Nicole"
            detail.text = "금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다"
        } else if(name == "갤럭시Z플립3 팝니다") {
            image.setImageResource(R.drawable.sample5)
            user.text = "절명"
            detail.text = "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!"
        } else if(name == "프라다 복조리백") {
            image.setImageResource(R.drawable.sample6)
            user.text = "미니멀하게"
            detail.text = "까임 오염없고 상태 깨끗합니다\n정품여부모름"
        } else if(name == "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장") {
            image.setImageResource(R.drawable.sample7)
            user.text = "굿리치"
            detail.text = "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다."
        } else if(name == "샤넬 탑핸들 가방") {
            image.setImageResource(R.drawable.sample8)
            user.text = "난쉽"
            detail.text = "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n + \"색상 : 블랙\"\n + \"사이즈 : 25.5cm * 17.5cm * 8cm\"\n + \"구성 : 본품더스트\"\n + \"급하게 돈이 필요해서 팝니다 ㅠ ㅠ\""
        } else if(name == "4행정 엔진분무기 판매합니다.") {
            image.setImageResource(R.drawable.sample9)
            user.text = "알뜰한"
            detail.text = "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n"
        } else {
            image.setImageResource(R.drawable.sample10)
            user.text = "똑태현"
            detail.text = "22년 신세계 대전 구매입니당\n + \"셀린느 버킷백\"\n + \"구매해서 몇번사용했어요\"\n + \"까짐 스크래치 없습니다.\"\n + \"타지역에서 보내는거라 택배로 진행합니당!\""
        }
    }
}
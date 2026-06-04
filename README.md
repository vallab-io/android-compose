# android-compose
## Step1 구현 기능 목록

1. Coil 라이브러리 및 INTERNET 권한 추가
   - libs.versions.toml, app/build.gradle.kts에 Coil 의존성을 추가한다.
   - AndroidManifest.xml에 INTERNET 권한을 추가한다.
2. Product 모델 및 더미 데이터 추가
   - Product data class를 정의한다.
   - imageUrl은 URL(String) 형태로 제공한다.
   - products 목록으로 샘플 상품 데이터를 추가한다.
3. ProductItem 및 CartScreen 구현
   - LazyColumn을 위한 ProductItem 추가
   - Scaffold, TopAppBar, LazyVerticalGrid(2열)로 상품 목록을 표시한다.


## Step2 구현 기능 목록

1. ProductDetailScreen 및 ProductDetailActivity 구현
   - 상품 상세 화면을 구현한다.
2. CartDetailScreen 및 CartDetailActivity 구현
   - 뒤로 가기로 직전 화면으로 돌아간다.
3. 장바구니 화면 이동 연결
   - 상품 목록 장바구니 아이콘 → CartDetailActivity
   - 상품 상세 장바구니 담기 버튼 → CartDetailActivity


## Step3 구현 기능 목록
1. 상품을 장바구니에 담는 기능을 구현한다.
   - CartItem 및 Cart model 추가
2. 장바구니 화면(CartDetailScreen) 구현
   - 담긴 상품의 수량을 조절할 수 있어야 한다.
   - 수량을 1보다 작게 하면 장바구니에서 상품이 제거된다.
   - 담긴 상품 가격의 총합이 주문하기 버튼에 표시된다.

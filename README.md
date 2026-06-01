# android-compose
## 구현 기능 목록

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

# android-compose


## Step1 GitHub — 데이터 레이어
1. GitHub API Client 구현
    - `GET https://api.github.com/orgs/next-step/repos` 호출
    - `full_name`, `description` 필드만 사용
2. data 패키지에 네트워크 요청 구현
3. 수동 DI 구성 (Hilt 미사용)
4. 서버 데이터 Logcat 확인


## Step2 GitHub — UI 레이어
1. NEXTSTEP 조직의 저장소 목록을 선형 리스트로 노출
2. Material3 Theme의 Typography, Color를 활용
3. ViewModel Factory를 구현
4. 저장소 목록을 노출하는 UI는 ui 패키지에 구현


## Step3 GitHub — UI상태
1. 목록이 로딩되기 전에는 로딩 UI를 노출한다.
   - 로딩 UI를 노출할 때 CircularProgressIndicator를 활용한다.
3. 목록이 빈 경우에는 빈 화면 UI를 노출한다.
4. 오류가 발생한 경우 재시도 가능한 스낵바를 노출한다.

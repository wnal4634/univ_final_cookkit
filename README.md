# Cookkit

졸업작품으로 제작한 레시피 공유 및 밀키트 판매 앱입니다.

<img src="" width="150"/> <br/><br/>

인트로 화면이 자동으로 넘어가면 로그인페이지로 이동합니다. 아래 '자동로그인' 체크박스를 체크하면 sharedpreferences에 아이디 정보를 저장해 다음 접속 시 별도의 로그인 없이 실행할 수 있습니다.<br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/80b9f729-042f-4f99-91ba-9d84073c25ec" width="150"/> <img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/4727c260-e625-4c38-9335-e55a8885a185" width="150"/> <br/><br/>

로그인페이지에서 이동할 수 있는 회원가입 페이지입니다. 아이디는 중복확인해 다른 사용자와 중복되지 않게 설정합니다. 비밀번호는 비밀번호 확인을 위해 두 번 작성합니다. 
비밀번호와 비밀번호 확인이 다를 경우엔 토스트 메시지가 뜨며 회원가입을 진행할 수 없습니다. 우편번호는 카카오주소 API를 사용해 쉽게 작성할 수 있습니다.
이미지에는 나오지 않았지만, 스크롤을 해 하단으로 내려가면 회원가입 동의서를 확인할 수 있습니다.<br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/d3212c89-e534-45b8-a2cc-a86ff86eafec" width="150"/> <br/><br/>


로그인에 성공하면 환영한다는 토스트 메시지가 뜨며, 메인 페이지에서 사용자들이 작성한 레시피의 전체 목록을 볼 수 있습니다. 
레시피명과 카테고리, 조회수가 있으며, 이 레시피 리스트를 위에서 잡아당기면 새로고침을 하고, 새로 작성된 레시피들을 확인할 수 있습니다.
상단의 최신순 버튼을 누르면 최신순과 조회수순을 고를 수 있는 목록이 나옵니다. 선택하는 것에 따라 레시피 목록을 최신순이나 조회수순으로 볼 수 있습니다. 기본값은 최신순입니다.<br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/13fb9697-5fff-4562-a276-8c1c51020221" width="150"/> <br/><br/>


메인페이지에서 레시피를 클릭하면 클릭한 아이템의 레시피 아이디와 데이터베이스에 저장된 레시피 아이디를 비교해 해당하는 레시피를 보여주는 상세페이지로 이동합니다. 
작성자부터 레시피명, 카테고리, 재료, 조리 순서와 이미지를 볼 수 있습니다. 레시피 상세페이지에 들어오면 해당 레시피의 조회수에 1이 더해집니다. 
그 옆의 좋아요 버튼을 누르면 마이페이지에서 좋아요한 레시피만 모아서 보는게 가능합니다. <br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/9ff4fa58-4e7b-4aa4-9e42-7e020320214b" width="150"/> <br/><br/>


메인페이지 하단의 플러스 플로팅 버튼을 누르면 레시피 작성 페이지로 이동하고, 사진과 텍스트를 입력합니다. 작성 시 하나라도 입력이 되지 않은 경우에는 모두 입력해달라는 메시지가 뜹니다.
모든 사진과 텍스트를 넣고 업로드를 하면 데이터베이스에 저장이 되고, 잠시 후 메인페이지에 목록이 뜨게 됩니다.<br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/ce9b0a4c-d1d4-4820-bd56-44869597f8ed" width="150"/> <br/><br/>


하단 메뉴바에서 '찾아보기'를 누르면 레시피 검색창으로 이동합니다. 상단의 검색창에서 레시피명을 검색할 수 있습니다. 중단의 각 카테고리를 클릭하면 해당하는 레시피별로 모아 볼 수 있습니다.<br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/4ea347f9-017a-4699-95b2-40098bebbe68" width="150"/> <br/><br/>


하단 메뉴바의 '밀키트'를 누르면 밀키트 설명이 나오며, 주문페이지로 이동할 수 있습니다. 주문페이지에선 밀키트 개수, 개수와 맞는 금액이 뜨고 배송지를 입력할 수 있습니다.
'기존배송지'를 클릭하면 이전에 회원가입 시 입력했던 주소를 자동으로 입력해줍니다. 모든 정보를 입력하고 하단의 결제하기 버튼을 누르면 결제 연동 서비스로 이동해 결제를 마칠 수 있습니다.
모든 정보를 입력하지 않으면 결제를 진행할 수 없습니다. <br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/875e03ca-a484-410e-aaa4-edf1e275fdbe" width="150"/> 
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/c22b67c9-f9c8-4af6-99ce-94de63588410" width="150"/> 
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/fe3d3f50-3440-4888-bb80-f79a52a76b09" width="150"/> 
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/af8f9cbd-3cc3-480c-a358-f944664be178" width="150"/> 
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/9626a0c4-ca3b-4b2e-bc83-49502c1f487c" width="150"/> <br/><br/>


하단 메뉴바의 '마이페이지'를 클릭하면 회원정보 수정 및 작성한 레시피 등을 관리할 수 있습니다. 밀키트 투표하러 가기 버튼은 메인페이지의 우측 상단과 동일한 페이지로 이동합니다.
레시피 중 조회수가 높은 레시피 중 밀키트로 제작하고 싶은 레시피를 투표할 수 있습니다. 투표는 각 아이디당 한 번만 가능하며, 투표 전 레시피 클릭을 통해 레시피를 확인할 수 있습니다. 
회원정보 수정에선 회원 아이디를 상단에 보여주며, 전화번호와 주소를 변경할 수 있습니다. 로그아웃 버튼을 누르면 sharedpreferences에 저장되어 있던 정보가 삭제되면서 로그인 창으로 나가게 됩니다.<br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/fe301549-9ba1-4361-90cb-23b5260bfba4" width="150"/> 
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/c6820e95-ec6a-4533-a590-8c0507f50f72" width="150"/> 
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/8c27f9a1-fcaa-4150-bba3-06457a759b89" width="150"/> <br/><br/>


사용자가 쓴 글을 볼 수 있는 페이지 입니다. 작성한 레시피가 아무것도 없다면 작성한 레시피가 없다는 표시가 뜨고, 쓴 글이 있다면 목록들이 나타나게 됩니다. 각 레시피는 수정 및 삭제가 가능합니다.
레시피 수정을 위해 수정페이지로 들어가면 이미 작성한 내용을 그대로 보여주며, 전체를 다시 쓰지 않고 원하는 부분만 수정이 가능합니다.<br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/f7898307-f71d-464d-9c74-58b8076567cd" width="150"/> 
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/1353ea11-8edd-44d8-801e-9b37086a4b01" width="150"/> <br/><br/>


주문한 밀키트를 확인할 수 있습니다. 구매한 밀키트의 제품명, 이미지, 구매 개수와 금액, 구매한 시각이 뜹니다.<br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/2366c562-9faf-4cbc-8519-844132c2fb12" width="150"/> <br/><br/>


좋아요한 레시피는 북마크처럼 이용할 수 있습니다. 각 레시피를 클릭하면 해당하는 레시피창으로 이동합니다. 좋아요를 누른 레시피라면 레시피 상세페이지의 좋아요 버튼이 채워진 상태로 나타납니다.
그 상태에서 다시 한 번 누른다면 좋아요를 취소할 수 있습니다. <br/>
<img src="https://github.com/wnal4634/univ_final_cookkit/assets/90739311/b0d4e97a-8356-47f4-ad52-e5599a9f042f" width="150"/> 


공지사항은 웹 관리자페이지에서 작성한 공지사항들이 목록으로 나옵니다. 이때 공지를 클릭하면 펼쳐지면서 내용을 확인할 수 있습니다. 
마찬가지로 펼쳐진 공지를 재클릭하면 접는 상태로 만들 수 있습니다.
![image](https://github.com/wnal4634/univ_final_cookkit/assets/90739311/e61f1114-6459-4611-9b5c-30cd7468d959)
![image](https://github.com/wnal4634/univ_final_cookkit/assets/90739311/c8d98dd4-20ff-427e-86f8-db5822e78578)


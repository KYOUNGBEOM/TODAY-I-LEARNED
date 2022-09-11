## HTML & CSS
> [제대로 파는 HTML & CSS강좌](https://www.youtube.com/watch?v=TrC2x4N0XqY&t=5127s)를 듣고 학습한 내용 정리
> 
> [참고자료](https://developer.mozilla.org/ko/docs/Web/HTML)

### 1. 제목 태그와 문단 태그
> &lt;h1&gt; ~ &lt;h6&gt; - 제목(숫자가 높을 수록 낮은 단계)
> 
> &lt;p&gt; - 문단(각각의 줄바꿈이 됨 단, 기본스타일일 경우)
> 
> &lt;br&gt; - 줄바꿈(닫는 태그 필요없음)
> 
> &lt;hr&gt; - 가로줄(닫는 태그 필요없음)
> 
> & n b s p ; - 공백(스페이스를 강제할 때 사용)
### 2. 주석 달기
>주석으로 처리할 부분을 선택한 뒤
>
> 윈도위: Crtl + /
>
> 맥: Command + /
### 3. 종류와 중요도 관련 태그
> &lt;b&gt; - 글자를 굵게([사용 일람](https://developer.mozilla.org/ko/docs/Web/HTML/Element/b#%EC%82%AC%EC%9A%A9_%EC%9D%BC%EB%9E%8C))

> &lt;Strong&gt; - 중요한 내용임을 명시

> &lt;i&gt; - 글자를 기울임([사용일람](https://developer.mozilla.org/ko/docs/Web/HTML/Element/i#%EC%82%AC%EC%9A%A9_%EC%9D%BC%EB%9E%8C))

>&lt;em&gt; - 강조할 내용임을 명시

>&lt;sup&gt; - 위 첨자(지수,서수)

>&lt;sub&gt; - 아래 첨자(각주,변수,화학식)

>&lt;u&gt; - 철자 오류 표시

>&lt;s&gt; - 더 이상 유효하지 않은 정보 표시
### 4. 인용문 관련 태그
>&lt;blockqoute&gt; - 비교적 긴 인용문에 사용(cite 속성으로 출처 표시)

>&lt;cite&gt; - 저작물의 출처 표기(제목을 반드시 포함)

>&lt;q&gt; - 비교적 짧은 인용문에 사용(Cite 속성으로 출처 표시)

>&lt;mark&gt; - 인용문 중 강조할 부분, 사용자 행동과 관련된 부분 표시

>&lt;abbr&gt; - 머릿글자 표시
### 5. 목록 표시 관련 태그
>&lt;ul&gt; - 순서가 없는 목록

>&lt;ol&gt; - 순서가 있는 목록(Type, Start 사용 가능)

>&lt;li&gt; - 목록 아이텀(ul, ol 태그에 포함시켜 사용)
### 6. 용어와 정의 관련 태그
>&lt;dl&gt; - 설명,서술목록(정의 목록을 만듦, description list)

>&lt;dt&gt; - 용어의 제목(description term)

>&lt;dd&gt; - 용어 설명(description details)
### 7. 이미지 넣기
>src - 원본파일 경로(절대경로 또는 상대경로)

>alt - 대체 텍스트(원본파일 무효시 보임)

>title - 툴팁(alt의 대체제나 반복이 되어서는 안됨)

>width - 너비

>height - 높이

>[이미지 맵 생성 사이트](https://www.image-map.net/) - 특수한 경우 제외 사용 지양 
### 8. 표 사용하기
>&lt;table&gt; - 테이블

>&lt;caption&gt; - 표 설명 또는 제목

>&lt;tr&gt; - 테이블의 행

>&lt;td&gt; - 테이블의 데이터 셀

>&lt;thead&gt; - 테이블의 헤더 부분(tbody 앞에 위치)

>&lt;tbody&gt; - 테이블의 본문

>&lt;tfoot&gt; - 테이블의 풋 부분(tbody 뒤에 위치)

>&lt;th&gt; - 열 또는 행의 헤더(scope 속성으로 row(세로,행), col(가로,) 중 선택)

>&lt;colspan&gt; - 열 병합

>&lt;rowspan&gt; - 행 병합
### 9. 다른 곳으로 링크
>&lt;a href="연결할 주소" target="링크를 열 곳 옵션"&gt;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● target속성값: _ self(현재 창), _ blank(새 창), _ Parent(부모 프레임), _ top(최상위 프레임)

>&lt;a href="#id값"&gt;(id값 활용하여 사용, 이동하고자 하는 타겟으로 바로 이동)

>&lt;address&gt; - 주소 및 연락처 정보 포함

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● &lt;a href="<strong>mailto</strong>:이메일주소"&gt; - 이메일 링크 태그

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● &lt;a href="<strong>tel</strong>:전화번호"&gt; - 전화번호 연결 태그
### 10. 입력 관련 태그
>&lt;form&gt; - 정보 제출을 위한 태그 포함(autocomplete 속성:자동완성 여부 결정(기본은 on))

>&lt;input&gt; - 입력을 받는 요소(type속성 - radio, checkout, list, password, search, tel, number, range, date 등)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● placeholder="" - 빈 칸에 보이는 안내문(지워짐, text 관련)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● maxlength="" - 최대 길이(text 관련) 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● minlength="" - 최소 길이(최소길이 불만족시 submmit 거부, text 관련)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● max="" - 최대값(숫자 관련)
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● min="" - 최소값(숫자 관련)
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● step="" - 간격(숫자 관련)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● checked="" - 체크박스 & 라디오(체크됨 여부, 체크 관련)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● name="" - 라디오(다른 타입도 사용, 옵션들의 그룹, 체크 관련)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● accept="" - 받아들일 수 있는 파일 형식(파일 관련)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● multiple="" - 여러 파일 업로드 가능(파일 관련)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● value="" - 기본으로 설정된 값(수정가능, 공통)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● autofocus - 페이지 진입시 자동 포커스(공통)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● required - 필수입력(미입력시 제출 불가능, 공통)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● disabled - 입력 불가능, 전송안됨(공통(대부분))

>&lt;label&gt; - 인풋 요소마다의 레벨(for 속성 값을 인풋 id 속성과 연결, 인풋 클릭 영역 확장)

>&lt;button&gt; - 버튼(type속성에 submit, reset, button(기본 동작 없음)이 있음)

>&lt;fieldset&gt; - 폼 태그 내 항목들 그룹화(disabled 속성 사용시 포함된 입력요소 비활성화)

>&lt;legend&gt; - 필드셋 요쇼 제목 또는 설명
### 11. 옵션 관련 태그
> &lt;textarea<strong>cols="" rows=""</strong>&gt; - cols(글자수 단위 너비), rows(표시되는 줄 수)

>&lt;select&gt; - 옵션 태그 포함하여 사용(multiple 사용 가능 단, 드랍다운 대신 상자로 표시됨)

>&lt;option&gt; - 선택지(selected, radio의 checked와 같은 기능 / value, 실제로 전송될 값) 
### 12. 정도 관련 태그
>&lt;progress&gt; - 진행도 관련 태그(다운로드 진행상태 등)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● max(최대값, 기본:1)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● vaule(진행 수치, 자바스크립트로 변경)

>&lt;meter&gt;

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● min="", max=""(최소값과 최대값) 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● low="", high=""(전체 범위를 3등분)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● optimum=""(이상적인 값, 3개 구간 중 한 곳 위치)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;● value=""(실제 값)

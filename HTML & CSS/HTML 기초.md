## HTML & CSS
> [제대로 파는 HTML & CSS강좌](https://www.youtube.com/watch?v=TrC2x4N0XqY&t=5127s)를 듣고 학습한 내용 정리

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
>&lt;src&gt; - 원본파일 경로(절대경로 또는 상대경로)

>&lt;alt&gt; - 대체 텍스트(원본파일 무효시 보임)

>&lt;title&gt; - 툴팁(alt의 대체제나 반복이 되어서는 안됨)

>&lt;width&gt; - 너비

>&lt;height&gt; - 높이

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

-target속성값: _self(현재 창), _blank(새 창), _Parent(부모 프레임), _top(최상위 프레임)

>

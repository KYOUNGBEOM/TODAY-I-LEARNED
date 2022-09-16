### 1. font-style과 font-weight
- font-style은 글자를 기울일 때 사용한다.
추가적인 팁으로는 italic과 oblique는 둘 다 기울여 쓴 서체 같지만,
italic이 기울여 쓴 서체라면 oblique는 본래 서체를 기울여놓은 것이다.
이 둘은 한쪽이 없을 시 상호 교차되어 사용된다.
- font-weight는 글자의 굵기를 조절한다.
서체가 어떤 굵기를 지원하느냐에 따라 nomal과 bold중에 선택하거나 
100~900사이에서 100단위 수치를 사용한다. 예시: font-weight: 400; , font-weight: bold; ,
[굵기에 따른 이름 매핑](https://developer.mozilla.org/ko/docs/Web/CSS/font-weight#%EC%9D%BC%EB%B0%98%EC%A0%81%EC%9D%B8_%EA%B0%80%EC%A4%91%EC%B9%98_%EC%9D%B4%EB%A6%84_%EB%A7%A4%ED%95%91)

### 2. font-szie
- font-size는 글자의 크기를 지정한다.
- 크기를 지정하는 단위로는 px, %, em, rem가 있다.
- px는 절대값으로써 픽셀 단위이다.
- 100% = 1em으로, 부모 요소와의 상대적인 크기로 나타난다. 요소의 중첩에 영향을 받는다.
- rem은 html 요소와의 상대적 크기를 가지므로, 요소의 중첩에 영향을 받지 않는다.
- pt는 자주 사용되지는 않지만 1인치/72로 프린트할 컨텐츠에 사용한다.

### 3. text-deocoration과 text-transform
- text-decoration은 글을 꾸며주는데 사용한다.[사용일람](https://developer.mozilla.org/en-US/docs/Web/CSS/text-decoration)
- html, css와 같이 대문자로 작성된 텍스트는 capitalize에 영향을 받지않는다. 
- 종류로는 capitalize, lowercase, uppercase가 있다.

### 4. text-shadow
- text-shadow는 텍스트에 그림자를 줄 수 있다.
x좌표, y좌표, 흐림, 색 형식으로 그림자를 넣을 수 있다.
쉼표로 구분하여 여러개를 넣을 수 있다.
크롬 개발자 도구를 사용하면 손쉽게 값을 조정할 수 있다.(윈도우: Ctrl+shift +i)

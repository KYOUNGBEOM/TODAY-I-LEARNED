### 1. cursor속성
- 마우스 포인터 요소가 위에 있을 때 표시되도록 마우스 커서를 설정함.
- auto, default, none, not-allowed, pointer 등이 있음. [커서목록](https://developer.mozilla.org/en-US/docs/Web/CSS/cursor)

### 2. 요소를 숨기는 세 가지 방법
- opacity 속성을 0으로 설정시 모습만 감춤. 자리는 차지함. 스크린 리더에 따라 읽힐수도 안 읽힐수도 있음.
- visibility 속성을 hidden으로 하면 opacity 효과에 더해 포커스, 클릭 등도 무효하게 됨. 스크린 리더에도 읽히지 않음.
- display 속성을 none 으로 하면 opactiy+visibility 효과에 더해 자리도 차지하지 않게됨.

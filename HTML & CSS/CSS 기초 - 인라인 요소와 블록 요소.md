### 1. 블럭 요소와 인라인 요소의 차이
- [블럭 요소](https://developer.mozilla.org/ko/docs/Web/HTML/Inline_elements#%EC%9A%94%EC%86%8C_%EB%AA%A9%EB%A1%9D)는 사각형의 형태를 갖고 있으며, 너비와 높이, 안팎의 간격을 부여할 수 있다.
- [인라인 요소]((https://developer.mozilla.org/en-US/docs/Web/HTML/Block-level_elements#elements)는 텍스트처럼, 일정한 형태 없이 페이지의 흐름에 따라 다른 텍스트 및 컨텐츠와 어우러져 배치된다.

### 2. inline
- 기본너비: 컨텐츠만큼
- width, height: 무시
- 가로공간 공유
- margin: 가로만 적용
- padding: 가로만 적용, 세로는 배경색만 적용

### 3. block
- 기본너비: 부모의 너비만큼
- width, height: 적용
- 가로공간 독점
- margin(바깥쪽 여백): 모두 적용(상하상쇄)
- padding(안쪽 여백): 모두 적용

### 4. inline-block
- 기본너비: 컨텐츠만큼
- width, height: 적용
- 가로공간 공유
- margin(바깥쪽 여백): 모두 적용(상하상쇄)
- padding(안쪽 여백): 모두 적용

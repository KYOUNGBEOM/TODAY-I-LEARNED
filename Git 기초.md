## Git 기초
> [제대로 파는 Git & GitHub 강좌](https://www.youtube.com/watch?v=1I3hMwQU6GU&t=1443s)를 듣고 학습한 내용 정리
### 1. 사용자 이름과 이메일 설정
> git config --global user.name "이름"
> 
> git config --global user.email "이메일 주소"
### 2. 기본 브랜치명 변경
> git config --global init.defaultBranch 브랜치명
### 3. 저장소 생성
> git init
### 4. 저장소 작업내용 확인
> git status
### 5. 특정파일/폴더 배제
> 파일명을 .gitignore로 설정
- file.c(모든 file.c 제외)
- /file.c(최상위 폴더 file.c 제외)
- *.c(모든 .c 확장자 파일 제외)
- !not_ignore_this.c (.c 확장자이지만 무시하지 않을 파일) 
- logs(logs란 이름의 파일 또는 폴더와 그 내용들)
- logs/(logs란 이름의 폴더와 그 내용들) 
### 6. 변경사항 버전에 담기
> git add 이름 (이름 파일 담기)

> git add .(모든 파일 담기)

> git commit -m "커밋 메시지" (커밋 저장)

> git commit -am "커밋 메시지" (add 와 commit 한 번에 단, 새로 추가된 파일 없을 경우에만)
### 7. 과거로 돌아가는 2가지 방법
> git reset --hard 돌아갈 커밋 해시 (돌아가고 나면 이전 내용은 삭제)

>

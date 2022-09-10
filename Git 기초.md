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
### 4. 저장소 작업내용 확인 / 변경 사항 확인
> git status(작업내용확인)
> git log(지금까지 커밋을 통해 생성한 변경점 확인)
### 5. 특정파일/폴더 배제
> 파일명을 .gitignore로 설정
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- file.c(모든 file.c 제외)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- /file.c(최상위 폴더 file.c 제외)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- *.c(모든 .c 확장자 파일 제외)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- !not_ignore_this.c (.c 확장자이지만 무시하지 않을 파일) 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- logs(logs란 이름의 파일 또는 폴더와 그 내용들)
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- logs/(logs란 이름의 폴더와 그 내용들) 
### 6. 변경사항 버전에 담기
> git add 이름 (이름 파일 담기)

> git add .(모든 파일 담기)

> git commit -m "커밋 메시지" (커밋 저장)

> git commit -am "커밋 메시지" (add 와 commit 한 번에 단, 새로 추가된 파일 없을 경우에만)
### 7. 과거로 돌아가는 2가지 방법
> git reset --hard 돌아갈 커밋 해시 (돌아가고 나면 이전 내용은 삭제)

> git revert 돌아갈 커밋 해시 (돌아가고 나도 이전 내용 존재, 커밋하고 되돌아감) 

> revert 충돌시 충돌해결 후 git revert --continue

> revert 충돌해결 불가능시 git revert --abort

> git revert --no-commit 돌아갈 커밋 해시 (커밋하지 않고 되돌아감)

> git reset --hard (마지막으로 커밋된 지점으로 돌아감)
### 8. Branch 생성/이동/삭제
> git branch 이름(이름 브랜치 생성)

> git branch(브랜치 목록 확인)

> git switch 이름(이름 브랜치로 이동)

> git branch -d 이름(이름 브랜치 삭제 단, 지워질 브랜치에만 있는 내용의 커밋이 있을 경우 대문자 D 사용)

> git branch -m 기존 이름 새 이름(브랜치 이름 변경)
### 9. Branch를 합성하는 2가지 방법
> git merge 이름(merge는 reset으로 되돌리기 가능, merge 하기 전 해당 브랜치의 마지막 시점)

> git rebase 이름(main 브랜치는 뒤쳐져 있는 상황, 다시 main 브랜치로 돌아와서 merge 해주어야 함)
### 10. Branch 간 충돌
> merge 충돌시
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- 해결가능시 → 직접 해당 부분을 타이핑하여 수정 후 merge 진행
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- 해결불가능시 git merge --abort

> rebase 충돌시
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- 해결가능시 → 충돌부분 수정 → git rebase --continue  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- 해결불가능시 git rebase --abort
### 11. 원격 저장소 사용하기
> git remote add 원격저장소이름 원격저장소주소(로컬의 Git 저장소에 원격 저장소로의 연결)

> git push -u 원격저장소이름 원격브랜치이름(로컬 저장소의 커밋 내역들을 원격으로 업로드)

> git remote(원격목록보기)

> git remote remove 원격저장소이름(로컬 프로젝트와의 연결을 없앰)

> git clone 원격저장소주소(대상 폴더에서 git bash 실행 후 명령어 입력)
### 12. Push와 Pull
> git push(git push -u 원격저장소이름 원격브랜치이름 으로 대상 원격 브랜치 지정 후 가능)

> git pull(원격의 커밋을 로컬로 가져옴)

> Push 할 것이 있을 시 Pull 하는 두 가지 방법
- git pull --no-rebase(merge 방식, 로컬과 원격의 어긋한 내용을 하나로 합침 )
- git pull --rebase(원격의 내용을 먼저 붙이고, 이후에 로컬의 내용을 붙임)

> git push --force(로컬의 내역 강제 Push, 협업간 반드시 상의 후 사용)
### 13. 로컬에서 브랜치 만들어 원격에 Push
> git push -u 원격저장소이름 로컬에서만든브랜치이름

> git branch --all(로컬과 원격 브랜치 모두 확인)
### 14. 원격 브랜치를 로컬로 Pull
> git fetch (원격의 변경사항 확인)

> git switch -t 원격저상소이름/원격브랜치이름(로컬에서 같은 이름 브랜치 생성 후 연결하고 스위치)
### 15. 원격 브랜치 삭제
> git push 원격저장소이름 --delete 원격브랜치이름

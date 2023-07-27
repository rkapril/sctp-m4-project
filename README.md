## Check branches

```
git branch
```

```
git checkout main
git checkout -b develop
git add ./
git commit -m "added some text"
git push origin develop

git checkout -b release/0.1.0
git add ./
git commit -m "added some text"
git push origin release/0.1.0

git checkout develop
git merge release/0.1.0
git checkout main
git merge develop
git push
git branch -d release/0.1.0
git branch -d develop
```

Added from develop branch...
Added from release/0.1.0 branch...

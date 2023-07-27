# Git Command

## Check branches

```
git branch
git branch -a
```

## Switch branch

```
git checkout develop
```

## Develop & Release branches
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
git push origin develop
git checkout main
git merge release/0.1.0
git push
git branch -d release/0.1.0
git push origin --delete release/0.1.0
git branch -d develop
git push origin --delete develop
```

Added from develop branch...

Added from release/0.1.0 branch...

# Step 1: Get the new version with `git cliff --bumped-version`
$newVersion = & git cliff --bumped-version

Write-Host $newVersion

# Step 2: Strip "v" prefix from its output
$versionWithoutPrefix = $newVersion -replace '^v', ''

# Path to the csproj file
try {
    $buildPath = Get-Item -Path "app\build.gradle.kts" -ErrorAction Stop
    $item.FullName
} catch {
    Write-Host "The specified file does not exist."
}

# Load the file
$content = Get-Content $buildPath

# Find the Version element and update its value
$pattern = 'versionName\s*=\s*".*?"'
$content = $content -replace $pattern, "versionName = `"$versionWithoutPrefix`""

# Save the updated XML file
try
{
    Set-Content -Path $buildPath -Value $content
}
catch [System.Exception] {
    "Error while saving file occured"
    throw
    exit 1
}

# Step 3: Create a commit named "chore(release): ${version-without-prefix}" with commit signing enabled
& git add $buildPath
$commitMessage = "chore(release): $versionWithoutPrefix"
& git commit -S -m $commitMessage

# Step 4: Tag this commit with the tag named with prefix "v" (original output of step 1)
& git tag -a $newVersion -m $newVersion

# Push the commit and the tag
& git up --tags

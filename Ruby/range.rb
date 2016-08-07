
# 1..5  # [1, 5]
# 1...5 # [1, 5)

p (1..5).class
p (1..100).select {|n| n.even?} .reduce {|n, k| n + k}
p (1..5).begin
p (1..5).end
p (1...100).include? 100

p (1..10).map {|n| n * 22}

p ('aa'...'ad').each {|v| p v}

# p (1.0..5.0)
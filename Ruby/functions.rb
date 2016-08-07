class Functions
  
  name = gets.capitalize!
  
  print #{name}
  
  int = Integer(gets.chomp)
  
  print int;
  
  puts ((1<<31)-1).lcm((1<<61)-1)
  
  5.upto(20) {
    |i|
    print i, ", "
  }
  
  puts 3.gcdlcm(-7)
  
  5.downto(1) { |n| print n.even?,"\n"}  
end

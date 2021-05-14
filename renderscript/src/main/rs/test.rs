#pragma version(1);
#pragma rs java_package_name(com.ggg.renderscript);

uint32_t name;

uchar4 RS_KERNEL invert(uchar4 in, uint32_t x,uint32_t y){
uchar4 out = in;
out.r = 255 - in.r;
out.g = 255 - in.g;
out.b = 255 - in.b;

return out;
}

//__attribute__((kernel)) 等价于 RS_KERNEL
uchar4 __attribute__((kernel)) greyScale(uchar4 in){
 uchar4 out = in;
 out.r = min(255, 0.393*out.r+0.769*out.g+0.189*out.b);
 out.g = min(255, 0.349*out.r+0.686*out.g+0.168*out.b);
 out.b = min(255, 0.272*out.r+0.534*out.g+0.131*out.b);

 return out;
}

void process(rs_allocation in, rs_allocation out){
const uint32_t imageWidth = rsAllocationGetDimX(in);
const uint32_t imageHeight = rsAllocationGetDimY(in);
rs_allocation temp = rsCreateAllocation_uchar4(imageWidth,imageHeight);
rsForEach(invert, in, temp);
rsForEach(greyScale, temp, out);
}
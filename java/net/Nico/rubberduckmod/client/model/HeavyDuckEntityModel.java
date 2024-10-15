package net.Nico.rubberduckmod.client.model;// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.Nico.rubberduckmod.RubberDuckMod;
import net.Nico.rubberduckmod.entity.Duck;
import net.Nico.rubberduckmod.entity.HeavyDuckEntity;
import net.Nico.rubberduckmod.entity.OreFinderDuck;
import net.Nico.rubberduckmod.entity.Quackling;
import net.Nico.rubberduckmod.entity.animations.ModAnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class HeavyDuckEntityModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation HeavyDuck = new ModelLayerLocation(new ResourceLocation(RubberDuckMod.MOD_ID, "heavy_duck"), "main");

	private final ModelPart wholebody;
	private final ModelPart mainbody;
	private final ModelPart Tail;
	private final ModelPart Head;
	private final ModelPart body;
	private final ModelPart LeftFoot;
	private final ModelPart RightFoot;

	public HeavyDuckEntityModel(ModelPart root) {
		this.wholebody = root.getChild("wholebody");
		this.mainbody = this.wholebody.getChild("mainbody");
		this.Tail = this.mainbody.getChild("Tail");
		this.Head = this.mainbody.getChild("Head");
		this.body = this.mainbody.getChild("body");
		this.LeftFoot = this.wholebody.getChild("LeftFoot");
		this.RightFoot = this.wholebody.getChild("RightFoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition wholebody = partdefinition.addOrReplaceChild("wholebody", CubeListBuilder.create(), PartPose.offset(-1.0F, 19.0F, -4.0F));

		PartDefinition mainbody = wholebody.addOrReplaceChild("mainbody", CubeListBuilder.create(), PartPose.offset(1.0F, 3.0F, 5.0F));

		PartDefinition Tail = mainbody.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(12, 10).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.0F, 1.0F));

		PartDefinition Head = mainbody.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 10).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(6, 16).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -3.0F));

		PartDefinition body = mainbody.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -3.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition LeftFoot = wholebody.addOrReplaceChild("LeftFoot", CubeListBuilder.create().texOffs(12, 14).addBox(0.0F, 0.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.0F, 5.0F));

		PartDefinition RightFoot = wholebody.addOrReplaceChild("RightFoot", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);

		// Check if the entity is sitting
		if (entity instanceof Duck duck && duck.isOrderedToSit()) {
			// Apply sitting pose
			this.LeftFoot.xRot = -1.5F;  // Rotate legs to simulate sitting
			this.RightFoot.xRot = -1.5F;
			this.body.xRot = 0.1F;  // Slight tilt to body

			// Adjust Y position to make it appear grounded when sitting
			this.wholebody.setPos(this.wholebody.x, this.wholebody.y + 1.5F, this.wholebody.z); // Lower model when sitting
		} else {
			// Normal walking animation
			this.animateWalk(ModAnimationDefinition.HeavyDuckAnimation.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
		}
	}



	private void applyHeadRotation(T entity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.Head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.Head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		wholebody.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return wholebody;
	}
}
